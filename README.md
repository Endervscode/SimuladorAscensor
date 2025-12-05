# Simulador de Ascensor - Java Swing  
Proyecto Universitario - Programación Orientada a Objetos

![Simulador de Ascensor]

## Descripción del Proyecto

Simulador visual y funcional de un ascensor de 3 pisos desarrollado en Java utilizando la biblioteca gráfica Swing.  
El programa reproduce con gran realismo el comportamiento de un ascensor real:

- Cabina que sube y baja con animación fluida  
- Puertas automáticas que se abren y cierran con deslizamiento  
- Display digital que muestra el piso destino y el piso actual  
- Botones de solicitud de pisos con desactivación durante el movimiento  
- Seguridad: el ascensor **nunca se mueve con las puertas abiertas**  
- Ventana de bienvenida al iniciar  

Ideal para demostraciones académicas, prácticas de POO y aprendizaje de interfaces gráficas en Java.

## Características Principales

| Funcionalidad                     | Estado  |
|-----------------------------------|---------|
| Animación suave de cabina         | Implemented |
| Apertura/cierre realista de puertas | Implemented |
| Display digital (piso actual/destino) | Implemented |
| Botones interactivos              | Implemented |
| Lógica de seguridad (puertas cerradas antes de moverse) | Implemented |
| Ventana de bienvenida             | Implemented |
| Diseño visual profesional         | Implemented |
| Código 100% comentado y estructurado | Implemented |

## Requisitos del Sistema

- Java 8 o superior (recomendado Java 17+)
- Sistema operativo: Windows, macOS o Linux

## Cómo Ejecutar el Programa

### Opción 1: Archivo .jar ejecutable (recomendado)

1. Descargar el archivo: `Ascensor.jar`
2. Hacer doble clic sobre el archivo  
   o desde terminal/consola:
```bash
java -jar Ascensor.jar
```

### Opción 2: Desde el código fuente (VS Code, IntelliJ, NetBeans)

1. Clonar o descargar este repositorio
2. Abrir la carpeta del proyecto en tu IDE
3. Ejecutar la clase principal: `SimuladorAscensor1.java`

## Estructura del Proyecto

```
Ascensor/
├── src/
│   └── SimuladorAscensor1.java    ← Código fuente completo
├── Ascensor.jar                   ← Archivo ejecutable
```

## Autor

**Estudiante:** Ender Moreno 
**Carrera:** Ingeniería en Informática  
**Universidad:** Universidad nacional Experimental De Guayana 
**Materia:** Técnicas de programación III
**Fecha:** 05 de Diciembre 2025

## Tecnologías Utilizadas

- **Java SE 17+**
- **Swing** (javax.swing y java.awt)
- **Timer** para animaciones
- Layout absoluto con diseño manual
- Programación orientada a objetos

## Licencia

Este proyecto es de uso académico y puede ser utilizado libremente para fines educativos.  
Se permite su modificación y distribución siempre que se mantenga el crédito al autor original.

---
