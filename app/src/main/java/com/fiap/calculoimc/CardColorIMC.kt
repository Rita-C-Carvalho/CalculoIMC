import androidx.compose.ui.graphics.Color

fun cardColor(imc: Double): Int{
    var cor = 0
    if (imc > 18.5 && imc < 25){
        cor = 1
    }else if (imc >= 25 && imc < 30){
        cor = 2
    }else if (imc >= 30){
        cor = 3
    }else if (imc < 18.5 && imc > 1){
        cor = 4
    }
    return cor
}






fun colorRow(cor: Int): Color{
    var color = Color.Unspecified
    if (cor == 1){
        color = Color.Green
    }else if (cor == 2){
        color = Color.Yellow
    }else if (cor == 3){
        color = Color.Red
    }else if (cor == 4){
        color = Color.Magenta
    }
    return color
}