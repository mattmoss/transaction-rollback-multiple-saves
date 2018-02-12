package demo

class Item {

    String name

    static constraints = {
        name blank: false, unique: false
    }

    static mapping = {
        version false
    }

}
