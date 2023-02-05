# Carlos Esteban Quezada Pelaez
## _Prueba tecnica conocimientos android mobile_


Esta es una prueba tecnica presentada a Yape con el proposito de mostrar los conocimientos que poseo sobre el desarrollo de apps para dispositivos android.

## Algunas librerias usadas:
- Inyeccion de dependencias con KOIN.
- Manejo de threading con Coroutines.
- Material Components.
- LLamadas de red con Ktor.
- Manejo de imagenes con Glide.
- Navigation Component.
- Splash Screen API.
- Google maps
- Unit testing con mockk

## Arquitectura:
El proyecto funciona bajo una estructura multimodular cuyos componentes son: app, core , recipe_dashboard, se implementan lineamientos de clean code, y se aplica una arquitectura limpia bajo un patron de presentacion MVVM


## Modulos

- app : Modulo encargado contener la pantalla de entrada a la aplicacion (Splash Screen) , su objetivo es lanzar la aplicacion. 
- core: Modulo  que permite compartir ciertas clases que son comunes ante otros modulos y tambien algunas dependencias.
- recipe_dashboard:  Este modulo contiene todo el flujo correspondiente al dashbboard de las aplicacion de recetas.  


## Estructura del proyecto
```text
recetas
  ├── :app
  ├── :core
  ├── :features
      └── :recipes_dashboard
```
