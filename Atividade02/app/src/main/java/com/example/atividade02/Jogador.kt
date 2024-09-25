package com.example.atividade02

data class Jogador(
    var nome: String,
    var level: Int,
    var bonusEquipamento: Int,
    var modificadores: Int
) {
    val poderTotal: Int
        get() = level + bonusEquipamento + modificadores
}