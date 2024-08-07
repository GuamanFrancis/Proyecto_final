-----------------------------------------
              MANUAL DE USUARIO
                Proyecto Final
   Sistema de ventas de accesorios celulares
-----------------------------------------

Autor: Francis Guaman  
Fecha: 08 de agosto de 2024

-----------------------------------------
              TABLA DE CONTENIDOS
1. Introducción  
2. Requisitos del Sistema  
3. Instalación  
4. Uso del Programa  
5. Solución de Problemas Comunes  
6. Conclusiones  
-----------------------------------------

1. INTRODUCCIÓN
-----------------------------------------
Este proyecto es una aplicación diseñada para simular 
un sistema de venta en el cual se debe poder restringir el
ingreso del personal "Cajero" al personal de "Administración",
dado que cada usuario tiene diferentes funciones que puede 
realizar dentro de la aplicación.

Su objetivo es generar ventas y poder registrar cada venta
para posteriormente generar una factura en formato PDF que
recoga todos los datos de la venta para su correcta entrega 
al cliente.

-----------------------------------------
2. REQUISITOS DEL SISTEMA
-----------------------------------------
- Sistema Operativo: Windows  
- Memoria RAM: 8 GB mínimo  
- Espacio en Disco: 200 GB  
- apache.pdfbox  
- mysql-connector-j-9.0.0.jar o superior  

-----------------------------------------
3. INSTALACIÓN
-----------------------------------------
1. Clona el repositorio desde GitHub: https://github.com/GuamanFrancis/Proyecto_final.git  
2. Abre IntelliJ IDEA.  
3. Selecciona File > Open y navega a la carpeta donde clonaste el repositorio.  
4. Espera a que IntelliJ descargue las dependencias automáticamente (si las tienes configuradas).  

-----------------------------------------
4. USO DEL PROGRAMA
-----------------------------------------
### Elegir Usuario
1. Abre la aplicación.  
2. Escoge entre Administrador o Cajero.  
3. Credenciales Administrador:  
   - User: francis  
   - Password: 123  
   Credenciales Cajero:  
   Se pueden crear usuarios cajeros dentro del modo administrador  
   o se pueden utilizar las credenciales de prueba:  
   - User: josue  
   - Password: 123  
4. Ingresa las credenciales.  

### Ventanas menu cajero o administrador
1. Escoge entre las ventanas dependiendo del tipo de usuario
   que se ingresó.  
2. Escoge entre las ventanas del menú administrador.  
3. Escoge entre las ventanas del menú cajero.  

### Registrar Productos y Cajeros
1. Abre la ventana.  
2. Completa los campos requeridos para ingresar productos.  
3. Haz clic en "Registrar Producto".  
4. Completa los campos requeridos para ingresar cajeros.  
5. Haz clic en "Registrar Cajero".  
6. Presiona "Mostrar Productos" para ver todos los productos.  
7. Presiona "Mostrar Cajeros" para ver todos los cajeros.  

### Mostrar Ventas 
1. Abre la ventana.  
2. Presiona "Mostrar" para ver todas las ventas.  
3. Presiona "Mostrar" para ver todos los cajeros.  
4. Ingresa el username del cajero.  
5. Haz clic en "Buscar" para mostrar las ventas de un solo cajero.  

### Registrar Compra
1. Abre la ventana.  
2. Completa los campos requeridos para mostrar el producto con detalles.  
3. Haz clic en "Buscar" y genera la búsqueda.  
4. Completa los campos requeridos para ingresar clientes.  
5. Haz clic en "Registrar".  
6. Presiona "Mostrar Productos" para ver todos los clientes.  
7. Presiona "Limpiar" para limpiar los campos.  
8. Completa los campos requeridos para generar la venta.  
9. Haz clic en "Comprar" y genera la factura.  

### Mostrar Facturas 
1. Abre la ventana.  
2. Presiona "Mostrar Facturas" para ver todas las facturas.  
3. Presiona sobre la factura que se desea imprimir.  
4. Presiona "Imprimir" para generar la factura en formato PDF.  

### Navegación
1. Presiona "Volver" para regresar a la ventana anterior.  
2. Presiona "Minimizar" para minimizar la ventana.  
3. Presiona "Cerrar" para salir del programa.  

-----------------------------------------
5. SOLUCIÓN DE PROBLEMAS COMUNES
-----------------------------------------
- Pregunta 1: ¿Cómo puedo cambiar la ruta donde se guardan las facturas en PDF?  
  - Respuesta: Para cambiar la ruta de guardado, edita el método que genera el PDF
  y ajusta la variable `rutaSalida` a la ubicación deseada. Asegúrate 
  de que la carpeta de destino exista.

- Pregunta 2: ¿Qué debo hacer si la aplicación no se inicia?  
  - Respuesta: Asegúrate de que tengas el JDK correcto instalado y que las variables
  de entorno estén configuradas. Verifica también si has clonado 
  correctamente el repositorio y que todas las dependencias están instaladas.

- Problema 1: Al intentar registrar una venta, recibo un error de "campo vacío".  
  - Solución: Asegúrate de que todos los campos requeridos en el formulario estén
  completados antes de enviar la información. Revisa que no haya espacios 
  en blanco.

- Problema 2: La factura generada está vacía o incompleta.  
  - Solución: Verifica que los datos de la venta se hayan guardado correctamente en 
  la base de datos. Asegúrate de que se están recuperando correctamente 
  en el método que genera el PDF. Comprueba también si hay datos en la 
  tabla de detalles de ventas.

- Problema 3: No puedo ver las imágenes en las facturas PDF.  
  - Solución: Asegúrate de que la ruta de la imagen que se está intentando incluir
  en el PDF sea correcta. Verifica que el archivo de imagen exista en 
  la ubicación especificada.

- Problema 4: Al intentar imprimir la factura, la aplicación se cierra inesperadamente.  
  - Solución: Verifica la consola de errores de IntelliJ para identificar el problema.
  Puede ser que falten permisos de escritura en la carpeta donde se intenta 
  guardar la factura o que haya un error en el formato del PDF.

- Problema 5: La aplicación se congela al cargar datos.  
  - Solución: Asegúrate de que la base de datos esté en funcionamiento y que la conexión
  sea correcta. Si los datos son muchos, considera implementar una paginación
  en la visualización de las ventas.

-----------------------------------------
6. CONCLUSIONES
-----------------------------------------
Este proyecto es una aplicación de gestión de ventas que permite al cajero realizar
compras, registrar cada venta y generar facturas en formato PDF. 
Las principales funcionalidades incluyen:

- Registro de Productos: Permite ingresar información detallada sobre los productos, 
  incluyendo nombre, descripción, precio, cantidad en stock e imágenes.
  
- Registro de Ventas: Los cajeros pueden registrar ventas de productos, asociando 
  cada venta a un cliente específico y generando un registro en la base de datos.
  
- Generación de Facturas en PDF: La aplicación permite generar y guardar facturas 
  en formato PDF de las ventas realizadas, lo que facilita la entrega de comprobantes 
  a los clientes.
  
- Interfaz Intuitiva: La interfaz de usuario está diseñada para ser amigable y fácil
  de usar, lo que permite a los usuarios navegar por la aplicación sin complicaciones.

### Posibles Mejoras Futuras:
1. Reportes avanzados: Agregar la funcionalidad para generar reportes detallados
   sobre ventas.

2. Mejora en la generación de PDF: Agregar opciones de personalización para las facturas generadas.
  
3. Implementar todas las excepciones: Agregar validaciones en los apartados donde se los necesite.
