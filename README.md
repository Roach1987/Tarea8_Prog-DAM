Tarea 8.1

Tarea para PROG08.
Detalles de la tarea de esta unidad. Enunciado.

*** Imagina que te proporcionan, en el siguiente formato, los datos de un cliente, con todos sus teléfonos y todos sus direcciones de correo electrónico:

DNI, "nombre", "apellidos", teléfono 1, teléfono 2, email 1, teléfono 3, email 2,...

En una misma línea se encuentran, separados por comas, todos los datos del cliente: DNI (o NIE), nombre, apellidos, teléfonos y direcciones de correo electrónico. Fijate que los teléfonos y los correos electrónicos pueden aparecer desordenados, y que pueden ser más de uno. La idea es meter dichos datos en un documento XML que contenga los datos de contacto del cliente. No es necesario leer los datos de un archivo, basta con que se capturen del teclado. Además, el documento XML solo contendrá los datos de un cliente, por lo que no debes preocuparte de procesar múltiples líneas (solo una).

*** A la hora de procesar los datos tienes que tener en cuenta los siguientes aspectos:

    El DNI o NIE siempre aparecerá en primer lugar.
    El nombre siempre aparecerá en segundo lugar, entre comillas. Las comillas habrá que quitarlas, no pueden aparecer en el      documento XML.
    Los apellidos siempre aparecerán en tercer lugar, entre comillas. Nuevamente, las comillas habrá que quitarlas.
    Los teléfonos y los correos electrónicos aparecerán desordenados y mezclados entre sí, pero en el XML resultante deben aparecer separados en dos partes: teléfonos y correos electrónicos por separado.
    Los teléfonos pueden tener paréntesis para simbolizar el prefijo ("(91)2345678") o en el código del país. Estos paréntesis habrá que eliminarlos al almacenarlo en el documento XML.
    Los teléfonos además pueden tener el símbolo "+" delante. Este hay que dejarlo, dado que simboliza que el número es un número internacional.
    Los teléfonos deben aparecer ordenados. Primero aparecerán ordenados de mayor a menor los números locales (sin símbolo "+" delante) y después los internacionales, también ordenados de mayor a menor.
    Los correos electrónicos deben almacenarse en minúsculas, aunque en la entrada vayan en mayúsculas.
    La lista de teléfonos y de correos no debería tener duplicados (tomando mayúsculas y minúsculas como lo mismo). Ten en cuenta que los paréntesis sobre un mismo número de teléfono no deben provocar la existencia de teléfonos diferentes en el XML.
    Es importante eliminar espacios sobrantes antes y después de cada trozo de texto extraído para procesarlo adecuadamente.

*** Las características del documento XML a generar son las siguientes:

    El elemento raíz se llamará "datos_cliente".
    El DNI o el NIE se almacenará en un elemento llamado "id".
    El nombre siempre se almacenará en un elemento llamado "nombre".
    Los apellidos siempre se almacenarán en un elemento llamado "apellidos".
    Los teléfonos se almacenarán todos en un elemento llamado "telefonos" (sin acento). Dentro de dicho elemento, habrá sub-elementos, llamados "telefono" (sin acento), cada uno de los cuales contendrá un único teléfono. Los teléfonos deberán aparecer ordenados tal y como se comentó en el párrafo anterior.
    La etiqueta "telefonos" deberá tener un atributo llamado "total" que contendrá el número total de teléfonos de la lista.
    Los correos electrónicos se almacenarán en un elemento llamado "mails". Dentro de dicho elemento, habrá sub-elementos llamados "mail" destinados a almacenar cada uno de los correos electrónicos del usuario.

Puedes usar los siguientes datos de prueba en tu aplicación (cada línea debería generar un documento XML):

X12345678F,"nombre","apellidos",+(82)12345678, 612345678,test@TEST.com,(91)23456789 ,prueba@prueba.com

12345678Z,"nombre","apellidos", prueba@prueba.com,(952)333333,test@test.com ,952333333,test@TEST.com

No olvides ser descriptivo o descriptiva en los errores. Si la línea proporcionada contiene errores, el programa no debería quedarse bloqueado, sino que debería informar de que se ha producido un error, e intentar ser descriptivo en el error detectado.

