# recipes-android

Este proyecto es una demostración de cómo usar MVVM, Clean Architecture y Compose para crear una aplicación de Android.
También se encuentra disponible su versión en iOS [recipes-ios](https://github.com/leinaro/recipes-ios).

##Requisitos previos
Este proyecto se desarrollo bajo las siguientes caracteristicas
- Android Studio Flamingo | 2022.2.1 Patch 2

##Instalación
- Clonar el repositorio
- Abrir el proyecto en Xcode
- Para el correcto funcionamiento del proyecto se debe agregar al local.properties la propiedad GMS_SERVICES_API_KEY que se obtiene desde la consola de google [Cómo usar claves de API](https://developers.google.com/maps/documentation/android-sdk/get-api-key?hl=es-419#:~:text=Ve%20a%20la%20p%C3%A1gina%20Google%20Maps%20Platform%20%3E%20Credenciales.&text=En%20la%20p%C3%A1gina%20Credenciales%2C%20haz,API%20que%20acabas%20de%20crear.)
- Construir y ejecutar el proyecto

##Uso
El proyecto permite visualizar recetas de cocina, la aplicación consta de tres pantallas: 
- Lista y buscador de las recetas (Home Screen)
Muestra una lista de recetas disonibles para consultar y un
buscador que filtre las recetas por nombre o por sus ingredientes utilizados.
Cada item muestra la imagen y nombre de la receta.

- Detalles de la receta seleccionada (Detail Screen)
Muestra el detalle de la receta, que incluye: Imagen, Nombre, Descripción y Botón para la pantalla de mapa.
  
- Mapa geolocalización de origen de la receta
Mustra un marcador en el mapa que muestra el origen de la receta.

##Decisiones técnicas
###MVVM 
[MVVM](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=es-419) se utiliza para comunicar la capa de datos y la ui.

###Clean Architecture
[Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) se organizó el proyecto en tres niveles principales que pueden ser modulos a futuro:

- ui
- domain
- data
Puede encontrar más detalles en [recommended-app-architecture](https://developer.android.com/jetpack/guide?hl=es-419#recommended-app-architecture) y en el blog [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).

###Networking
[Retrofit](https://square.github.io/retrofit/) es un api que convierte el codigo kotlin a llamados HTTP y devuelve llamados como objetos kotlin.
[Okhttp Interceptor]() es un mecanismo para puede monitorear, reescribir y reintentar llamadas.
[Gson](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) es una biblioteca que permite convertir objetos Kotlin a JSON y viceversa. 

###Datos
Para la generación de datos se utilizó mockaroo. [Mockaroo](https://mockaroo.com/) es una herramienta en línea que te permite crear modelos de datos de prueba. Puedes usar Mockaroo para crear tablas, registros y campos de prueba. También puedes exportar tus modelos de datos de prueba en una variedad de formatos, incluidos JSON, CSV y XML.

###Compose
[Compose](https://developer.android.com/jetpack/compose?gclid=Cj0KCQjwz8emBhDrARIsANNJjS5j4cQlxMs49HRdxGD7IrXgeJA5Wccv59zTQdM9Iy89-6PezQJztZMaAv6dEALw_wcB&gclsrc=aw.ds&hl=es-419) es una API declarativa, lo que significa que puede describir la interfaz de usuario de su aplicación en términos de lo que debe verse, en lugar de cómo debe verse. 



###GoogleMaps   
El SDK de [Google Maps](https://github.com/googlemaps/android-maps-compose) para Android y compose es una biblioteca de software que permite a los desarrolladores agregar mapas a sus aplicaciones android. El SDK proporciona una amplia gama de funciones, incluyendo la capacidad de agregar marcadores, rutas y otras formas a los mapas, así como la capacidad de interactuar con los mapas de diversas maneras.

###Inyección de dependencias
[Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419) es una biblioteca de inserción de dependencias para Android que permite reducir el trabajo repetitivo de insertar dependencias de forma manual en tu proyecto.

###Internacionalización
Disponible en ingles y español

###Unit test
[Mockito](https://site.mockito.org/) is un framework que ayuda a crear mocks de las interfaces y objetos kotlin, brinda herramientas para desarrollar las pruebas unitarias.

## Developers

* [leinaro](https://github.com/leinaro)
