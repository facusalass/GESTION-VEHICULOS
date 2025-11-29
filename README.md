# Trabajo Final: Sistema de Gestión de Vehículos

## Descripción del Proyecto
Este proyecto es una aplicación de consola en Java que simula la administración de una flota de vehículos. El objetivo principal fue integrar todos los conceptos vistos en la cursada sobre **Programación Orientada a Objetos (POO)**.

El sistema permite dar de alta vehículos (diferenciando entre Autos y Motos), listarlos, buscarlos y registrar mantenimientos, asegurando que el programa sea robusto y no se rompa ante errores del usuario.

---

## Integrantes y División de Tareas
Para el desarrollo, dividimos la lógica en capas para tener un código más limpio:

* **Integrante 1:** Diseño del Modelo de Datos (Clases `Vehiculo`, `Auto`, `Moto`).
* **Integrante 2:** Lógica de Negocio y Colecciones (`GestionVehiculos`).
* **Integrante 3:** Interfaz de Usuario y Validaciones de entrada (`Main`).
* **Integrante 4 (Yo):** Manejo de Excepciones Personalizadas (`ElementoNoEncontradoException`), Documentación y armado final.

---

## Explicación Técnica por Opción del Menú

A continuación, detallamos qué hace cada función del sistema y qué consigna de la materia cumple:

### Opción 1: Agregar Vehículo
* **Funcionalidad:** Pide los datos al usuario. Lo interesante es que detecta si es "Auto" o "Moto" para pedir el dato específico (puertas o cilindrada) y luego lo guarda en una lista única.
* **Conceptos aplicados:**
    * **Herencia y Polimorfismo:** Aunque instanciamos objetos distintos (`new Auto` o `new Moto`), ambos se guardan en una lista de tipo `Vehiculo`.
    * **Manejo de Tipos:** Usamos `try-catch` básicos aquí para evitar que el programa se cierre si el usuario pone letras en el kilometraje.

### Opción 2: Listar Vehículos
* **Funcionalidad:** Muestra todos los vehículos cargados en memoria.
* **Conceptos aplicados:**
    * **Colecciones (List/ArrayList):** Recorremos la lista dinámica donde están guardados los datos.
    * **Sobrescritura (Override):** Cada clase tiene su propio método `toString()`, por lo que el listado se ve diferente y formateado según si el objeto es un Auto o una Moto.

### Opción 3: Buscar Vehículo
* **Funcionalidad:** El usuario ingresa una patente y el sistema busca si existe.
* **Conceptos aplicados:**
    * **Streams y Optional:** Usamos la API de Streams para filtrar la lista de forma eficiente. El resultado se maneja con un `Optional` para evitar el famoso error *NullPointerException* si no se encuentra nada.

### Opción 4: Registrar Mantenimiento 
* **Funcionalidad:** Se intenta cargar un servicio a un vehículo. Si la patente no existe, el sistema no falla silenciosamente ni se rompe.
* **Conceptos aplicados:**
    * **Excepciones Propias:** Creé la clase `ElementoNoEncontradoException`. En lugar de devolver un simple `null` o `false`, el método **lanza** (throws) esta excepción específica.
    * **Bloque Try-Catch-Finally:** En el `Main`, encerramos esta llamada en un bloque de control. Si la patente no existe, "atrapamos" el error y le mostramos al usuario un mensaje claro (`⛔ ERROR: ...`), demostrando un control de flujo robusto.

### Opción 5: Historial de Usuario
* **Funcionalidad:** Muestra las últimas 5 opciones que eligió el usuario.
* **Conceptos aplicados:**
    * **Arreglos (Arrays):** Usamos un vector estático `String[5]`.
    * **Algoritmos (Buffer Circular):** Para no tener que borrar y mover todo el array cada vez que se llena, usamos lógica matemática con el operador módulo (`%`). Esto hace que, al llegar a la posición 5, vuelva a empezar por la 0, guardando siempre lo más reciente.

---

## Cómo correr el proyecto
1. Abrir el proyecto en IntelliJ.
2. Ir a la clase `Main.java.
3. Darle a **Run**.
4. Usar la consola para interactuar con el menú.
