package classes_objects

//Sealed class is 'abstract' by itself
//has 'private constructor' by default
sealed class Expr

data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

//Key benifit of using sealed class comes into play when you use them in
//'when expression'
fun eval(expr: Expr): Double = when (expr) {
  is Const -> expr.number
  is Sum -> eval(expr.e1) + eval(expr.e2)
  NotANumber -> Double.NaN
}

fun main() {
}