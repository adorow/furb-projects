package math;

/**
 * Classe que representa uma matriz.
 */
public class Matrix {

    private double[][] m;
    private int rows, cols;

    /**
     * Cria uma nova matriz.
     * 
     * @param rows o n�mero de linhas.
     * @param cols o n�mero de colunas.
     */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.m = new double[rows][cols];
    }

    /**
     * Obt�m um valor da matriz.
     * 
     * @param row a linha onde est� o valor.
     * @param col a coluna onde est� o valor.
     * @return o valor encontrado.
     */
    public double get(int row, int col) {
        return m[row][col];
    }

    /**
     * Atribui um valor � matriz.
     * 
     * @param row a linha a ser alterada.
     * @param col a coluna a ser alterada.
     * @param value o valor a ser atribu�do.
     */
    public void set(int row, int col, double value) {
        m[row][col] = value;
    }

    /**
     * @return o n�mero de linhas.
     */
    public int getNumberOfRows() {
        return rows;
    }

    /**
     * @return o n�mero de colunas.
     */
    public int getNumberOfColumns() {
        return cols;
    }

    /**
     * Transforma esta matriz em uma matriz identidade.
     */
    public void makeIdentity() {
        if (rows != cols) return;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m[i][j] = (i == j) ? 1 : 0;
            }
        }
    }

    /**
     * Efetua a multiplica��o desta matriz por outra.
     * 
     * @param other a outra matriz.
     * @return a nova matriz contendo o resultado da multiplica��o das duas.
     */
    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) throw new IllegalArgumentException("Matriz inv�lida para a multiplica��o.");

        int r1 = this.rows;
        int c1r2 = this.cols;
        int c2 = other.cols;

        Matrix ret = new Matrix(r1, c2);

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1r2; k++) {
                    ret.m[i][j] += this.m[i][k] * other.m[k][j];
                }

            }
        }

        return ret;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        for (int i = 0; i < this.rows; i++) {
            sb.append("| ");
            for (int j = 0; j < this.cols; j++) {
                sb.append(String.format("%10.4f ", get(i, j)));
            }
            sb.append("|");
            sb.append("\n");
        }

        return sb.toString();
    }

}
