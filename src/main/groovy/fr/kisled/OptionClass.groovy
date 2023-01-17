package fr.kisled

import groovy.cli.Option
import groovy.cli.Unparsed

class OptionClass {
    @Option(shortName = 'o', description = 'Output file') String output
    @Option(shortName = 'e', longName = 'extension', description = 'Generated code extension (py or ipynb)') String extension
    @Unparsed List sources
}