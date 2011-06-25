# Introduction

Ego is a library for working with a simple id format.

Ego ids are in the format of "type-number". An example id would be "foo-1". This library was created to eliminate duplicated code in a few of our own projects, specifically [jiraph](https://github.com/flatland/jiraph) and [wakeful](https://github.com/flatland/wakeful).

## Usage

This library has a single function: split-id. It takes an id and optionally a function for verification.

    user=> (split-id "foo-1")
    [:foo 1]

And here is an example using verification functions:

    user=> (split-id "foo-2" #{:bar :foo})
    [:foo 2]
    user=> (split-id "foo-2" #{:bar :baz})
    java.lang.Exception: node-id foo-2 doesn't match type(s): bar, baz (NO_SOURCE_FILE:0)
    user=> (split-id "foo-2" #(= :foo %))
    [:foo 2]
