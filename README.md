Aplicação criada na IDE Spring Tool Suite 4 usando Maven como dependência.
Esta aplicação Java Swing lê QR Codes de um arquivo, exibe os dados em uma JTextArea e permite salvar os dados em um arquivo TXT, você pode usar a 
biblioteca zxing para leitura de QR Codes. A seguir está um exemplo de código que realiza essas funções:

Dependência zxing
-----------------
Primeiro, adicione a dependência zxing ao seu projeto. Se estiver usando Maven, adicione a seguinte dependência ao seu pom.xml:


<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>core</artifactId>
    <version>3.4.1</version>
</dependency>
<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>javase</artifactId>
    <version>3.4.1</version>
</dependency>



Como Funciona
-------------
Botão "Select QR Code File": Abre um seletor de arquivos para o usuário escolher um arquivo de imagem contendo o QR Code.
O conteúdo do QR Code é lido e exibido na JTextArea, e a imagem do QR Code é exibida em um JLabel.

Botão "Save to TXT File": Abre um seletor de diretórios para o usuário escolher onde salvar o conteúdo do QR Code em um arquivo TXT.

Observações

Certifique-se de que a imagem do QR Code esteja em um formato suportado pela biblioteca ImageIO (por exemplo, PNG, JPEG).
A biblioteca zxing é usada para decodificar o QR Code da imagem selecionada.
Este código cria uma interface gráfica simples com Java Swing que lê e exibe QR Codes de arquivos de imagem e permite salvar o conteúdo em um arquivo 
de texto.
