
class Name{
    var name:String = "Aditya "
        get() = field.toUpperCase()
       set(value){
           println(name)
           field = value.toLowerCase();
           println(field)
       }
}
fun main(){
val Name :Name = Name();
    println(Name.name)
    Name.name = "Mishra"
    println(Name.name)
}