package binarysnow.klctrie

data class Node(val branchBits: Byte, val skipBits: Byte, val children: List<Node>, val data: String = "")

data class Input(val ip: Int, val length: Byte, val info: String)