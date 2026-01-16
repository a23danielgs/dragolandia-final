# Dragolandia

## Introducción
Dragolandia es un proyecto sobre un juego de fantasía donde interactuan magos, monstruos, bosques, dragones y hechizos. 
El objetivo del proyecto es modelar la lógica del juego utilizando java y la persistencia en base de datos mediante Hibernate.

## Análisis
En esta fase se identificaron las principales entidades del juego:
- **Mago**: personaje con nivel de magia, vida y lista de hechizos.
- **Monstruo**: enemigo con tipo, fuerza, vida y posible relación con un bosque.
- **Bosque**: entorno donde habitan los monstruos y puede estar protegido por un dragón.
- **Hechizo**: habilidades mágicas que los magos pueden lanzar sobre monstruos o sobre sí mismos.
- **Dragón**: criatura poderosa que protege un bosque y puede atacar a monstruos con fuego.

Se definieron también las relaciones entre estas entidades, como jefe del bosque, monstruos asociados, hechizos de magos y dragón asociado al bosque.

## Diagrama de clases
El siguiente diagrama muestra la **estructura de clases del proyecto**, incluyendo atributos y relaciones principales:

![Diagrama de clases](img/DiagramaClases.svg)

## Diseño
### Entidad-Relación
![Entidad relación](img/EntidadRelacion.svg)

## Futuras ampliaciones
- Añadir un menú para poder seleccionar a monstruos y magos preexistentes en la base de datos para que puedan volver a combatir
- Guardar en la base de datos información sobre los combates realizados

## [Manual De Usuario](ManualUsuario.md)

## Tablas
📄 **[Ver PDF de las tablas](Dragolandia-hibernate.pdf)**