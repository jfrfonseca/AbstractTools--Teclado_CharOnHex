
Sistema de desenvolvimento de um design e layout de teclado que privilegie interfaces touch
===================
# Teclado CharOnHex #
===================

### Este trabalho foi desenvolvido por Matheus Pereira e José Fonseca como parte dos requisitos para graduação em Ciências da Computação pela PUC Minas.

## O teclado de todo dia foi pensado para máquinas de escrever antigas. Não para WhatsApp e Twitter.
A forma e disposição das teclas em um teclado típico, cujo início da primeira linha de letras contém a sequência "QWERTY", foi criado a mais de cem anos para otimizar o uso de máquinas de escrever alemâs. Decadas mais tarte, a disposição de teclas denominada "Dvorak" foi imaginada para otimizar a digitação em teclados eletrônicos.
Estes dois populares layouts de teclas de teclados, QWERTY e Dvorak, foram imaginados para o uso de multiplos dedos, em um teclado posicionado em uma mesa.
O popular aplicativo WhatsApp é hoje uma das principais formas de comunicação escrita entre pessoas, e sua interface é muito diferente de uma máquina de escrever antiga. Entre outros, é usada uma tela Touch.

## Swipe-Type
Uma forma comum de uso de telas touch é o chamado Swipe-type, quando um usuário posiciona seu dedo na tecla correspondente a primeira letra de uma palavra, e, sem despegar da tela, desliza o dedo sobre as demais letras da palavra. Um software automaticamente identifica a palavra desejada, e a imprime, inteira, num campo de texto que esteja sendo preenchido.
Swipe-Type é uma técnica comum para digitação em dispositivos móveis, pois exige e se beneficia de telas touch.
Mas palavras como Alfajor, Moedor, Palácios,e tantas outras, exigem um deslocamento excessivo do dedo sobre a tela, pois estão em lados opostos do teclado.
Esse esforço poderia ser minimizado se as teclas fossem dispostas no teclado de uma forma mais consiente, imaginada do princípio para minimizar o deslocamento do dedo em uma tela. Um layout de teclado imaginado especificamente para swipe-type.

## Teclados evoluidos
Tomando inspiração na natureza, utilizamos um chamado Algoritmo Genético para testar milhares de possibilidades de distribuições de letras nas teclas de um teclado, tentando descobrir o layout que resultasse na menor distância percorrida pelo dedo ao digitar uma palavra típica.
Testamos até para um teclado com uma disposição exagonal, com cada letra ocupando uma célula, tal qual numa colmetia de abelhas! Daí criamos o nome, Char On Hex.

# Sobre este repositório
Neste repositório, deixamos códigos e dados utilizados na elaboração do layout de teclado Char On Hex
