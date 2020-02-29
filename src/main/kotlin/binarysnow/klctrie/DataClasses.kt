package binarysnow.klctrie

data class Node<T>(val branchBits: Byte, val skipBits: Byte, val children: List<Node<T>>, val data: T?)

data class Input<T>(val ip: Int, val length: Byte, val data: T)
