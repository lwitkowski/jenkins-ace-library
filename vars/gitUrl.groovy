#!/usr/env/groovy

String call(String commit = '') {
  String rawUrl = sh(
    script: 'git config remote.origin.url',
    returnStdout: true
  )?.trim()

  List list

  if (rawUrl =~ /^https/) {
    list = rawUrl
      .replaceAll('https://', '')
      .replaceAll('.git', '')
      .split('/')

    list.remove(1)
  } else {
    list = rawUrl
      .replaceAll('ssh://git@', '')
      .replaceAll(/:[0-9]+/, '')
      .replaceAll('.git', '')
      .split('/')
  }

  String url = "https://${list[0]}/projects/${list[1]}/repos/${list[2]}/browse"

  if (commit != '') {
    return "${url}?at=${commit}"
  }

  return url
}
