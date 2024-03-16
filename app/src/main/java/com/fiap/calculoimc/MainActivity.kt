package com.fiap.calculoimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cardColor
import colorRow
import com.fiap.calculoimc.ui.theme.CalculoIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculoIMCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IMCScreen()
                }
            }
        }
    }
}

@Composable
fun IMCScreen() {
    //VARIÁVEL PARA GUARDAR O PESE
    var peso = remember {
        mutableStateOf("")
    }

    //VARIÁVEL PARA GUARDAR A ALTURA
    var altura = remember {
        mutableStateOf("")
    }

    //VARIÁVEL PARA GUARDAR O IMC
    var imc = remember {
        mutableStateOf(0.0)
    }

    //VARIÁVEL PARA GUARDAR O STATUS
    var statusImc = remember {
        mutableStateOf("")
    }
    
Box(modifier = Modifier.fillMaxSize()){
    Column (
        modifier = Modifier
            .fillMaxWidth()

    ){

        //HEADER
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(colorResource(id = R.color.deep_sky_blue))
        ){
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.balanca),
                contentDescription = "Imagem de uma balança",
                modifier = Modifier
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "CALCULADORA IMC",
                color = Color.White
            )

        }

        //FORMULÁRIO
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ){
            Card(
                modifier = Modifier
                    //.height(300.dp)
                    .fillMaxWidth()
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                //border = BorderStroke(width = 1.dp, Color.Blue),
                shape = RoundedCornerShape(8.dp)
            ){ Column (
                modifier = Modifier.padding(
                    vertical = 16.dp,
                    horizontal = 32.dp
                )
            ){
                Text(text = "Seus dados",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.deep_sky_blue),
                    textAlign = TextAlign.Center
                    )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Seu peso",
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.deep_sky_blue)
                )
                OutlinedTextField(
                    value = peso.value,
                    onValueChange = {peso.value = it},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Seu peso em Kg")
                    }, colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = colorResource(id = R.color.deep_sky_blue),
                        focusedBorderColor = colorResource(id = R.color.deep_sky_blue)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType =
                    KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Sua altura",
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = colorResource(id = R.color.deep_sky_blue)
                )
                OutlinedTextField(
                    value = altura.value,
                    onValueChange = {altura.value = it},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Sua altura em cm."
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = colorResource(id = R.color.deep_sky_blue),
                        focusedBorderColor = colorResource(id = R.color.deep_sky_blue)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType =
                    KeyboardType.Decimal)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                              imc.value = calcularIMC(
                                  altura = altura.value.toDouble(),
                                  peso = peso.value.toDouble()
                              )
                        statusImc.value = determinarClassificacaoIMC(imc.value)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor =
                    colorResource(id = R.color.deep_sky_blue))
                ) {
                    Text(
                        text = "CALCULAR",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
            }

        }

    }
        //CARD RESULTADO
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 32.dp, vertical = 24.dp)
            .align(Alignment.BottomCenter)
            .background(colorRow(cardColor(imc.value))),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxSize()
        ) {
            Column() {
                Text(
                    text = String.format("%.1f", imc.value),
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp) )
                Text(
                    text = "Resultado",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = statusImc.value,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )

            }

        }
    }


}
}



