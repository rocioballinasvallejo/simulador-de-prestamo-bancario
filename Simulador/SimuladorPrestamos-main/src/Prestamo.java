class Prestamo {
    private double monto;
    private double anticipo;
    private double tasaInteres;
    private int plazo;

    public Prestamo(double monto, double anticipo, double tasaInteres, int plazo) {
        this.monto = monto;
        this.anticipo = anticipo;
        this.tasaInteres = tasaInteres;
        this.plazo = plazo;
    }

    public double getMontoFinanciado() {
        return monto - anticipo;
    }

    public int getPlazo() {
        return plazo;
    }

    public double getTasaMensual() {
        return (tasaInteres / 100) / 12;
    }

    public double calcularCuotaMensual() {
        double P = getMontoFinanciado();
        double i = getTasaMensual();
        int n = plazo * 12;
        return (P * i * Math.pow(1 + i, n)) / (Math.pow(1 + i, n) - 1);
    }

    public double calcularTotalAPagar() {
        return calcularCuotaMensual() * plazo * 12;
    }

    public double calcularInteresesTotales() {
        return calcularTotalAPagar() - getMontoFinanciado();
    }

    public double calcularValorFuturo(double ahorroMensual, double tasaMensual, int meses) {
        return ahorroMensual * ((Math.pow(1 + tasaMensual, meses) - 1) / tasaMensual);
    }

    public double calcularValorPresente(double cuota, double tasaMensual, int meses) {
        return cuota * (1 - Math.pow(1 + tasaMensual, -meses)) / tasaMensual;
    }

    public double calcularVPN(double[] flujos, double tasaAnual) {
        double vpn = -getMontoFinanciado();
        double tasa = tasaAnual / 100;
        for (int t = 0; t < flujos.length; t++) {
            vpn += flujos[t] / Math.pow(1 + tasa, t + 1);
        }
        return vpn;
    }

    public double calcularCuotaPagoJusto(double valorPresente, double tasaMensual, int meses) {
        return valorPresente * ((tasaMensual * Math.pow(1 + tasaMensual, meses)) / (Math.pow(1 + tasaMensual, meses) - 1));
    }
}
