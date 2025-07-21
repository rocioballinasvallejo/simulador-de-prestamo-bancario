# 💰 Simulador de Préstamos Bancarios (Java - MXN)

Este proyecto es un **simulador de préstamos bancarios** con una interfaz gráfica desarrollada en Java (Swing), diseñado para calcular pagos, intereses y amortizaciones de forma precisa, con base en fundamentos financieros profesionales.

---

## 🚀 ¿Qué hace?

Permite realizar simulaciones realistas de préstamos con las siguientes funciones clave:

- 📊 **Resumen financiero**:
  - Monto financiado
  - Cuota mensual estándar
  - Cuota mensual con pago justo
  - Total a pagar
  - Intereses totales

- 📅 **Tabla de amortización mensual**:
  - Desglose de pagos mes a mes
  - Interés pagado por periodo
  - Capital amortizado
  - Saldo restante

Todo esto utilizando criterios como **pago justo sobre saldos insolutos**, conforme al análisis financiero profesional.

---

## 💠 Requisitos

- ☕ [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) instalado (versión 8 o superior)
- Editor de texto o IDE (opcional, recomendado: IntelliJ, VS Code, Eclipse)

---

## 📌 Cómo compilar y ejecutar

Abre una terminal desde la raíz del proyecto y ejecuta:

```bash
javac -d bin src/Prestamo.java src/InterfazPrestamo.java
java -cp bin InterfazPrestamo
```

---

## 🧼 Fórmulas y lógica financiera

### 1. 📈 Monto Financiado

```math
\text{Monto financiado} = \text{Monto del préstamo} - \text{Anticipo}
```

### 2. 📅 Cuota Mensual Estándar

```math
\text{Cuota mensual} = P \times \frac{i(1+i)^n}{(1+i)^n - 1}
```

- \( P \): Monto financiado
- \( i \): Tasa de interés mensual (anual / 12 / 100)
- \( n \): Plazo en meses

### 3. 📏 Cuota Mensual con Pago Justo

```math
\text{Cuota justa} = VP \times \frac{i(1+i)^n}{(1+i)^n - 1}
```

> Donde \( VP \) es el valor presente (sin sobrecargar de intereses anticipados).

### 4. 💳 Total a Pagar

```math
\text{Total a pagar} = \text{Cuota mensual} \times n
```

### 5. 💸 Intereses Totales

```math
\text{Intereses} = \text{Total a pagar} - \text{Monto financiado}
```

---

## 📖 Tecnología usada

- ✅ **Java SE**
- ✅ **Swing** (para la interfaz gráfica)
- ✅ **JTable** (para visualizar la tabla de amortización)

---

## 🧠 Basado en...

Este simulador fue diseñado con base en los principios de:
- Valor del dinero en el tiempo
- Tasa de interés compuesta
- Amortización bajo el criterio de pago justo
- Flujo de efectivo y análisis financiero

Referencias: análisis del documento “Valor del dinero - presente y futuro”.

---

## 👤 Autor
Creado por **Luis Alberto Batalla González, Gabriel Reyes, Rocio Vaginas**

---

## 📂 Repositorio

📂 [Repositorio GitHub](https://github.com/LuisAlbertoB/SimuladorPrestamos.git)
