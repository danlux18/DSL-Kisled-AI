package fr.kisled.kernel

class DataAcquisition extends CodeLine {
    final String path
    final String varname

    DataAcquisition(String path, String varname) {
        this.path = path
        this.varname = varname
    }
}
