package es.upm.dit.adsw.ej5;

/**
 * Un punto en un espacio 2D.
 *
 * @author jose a. manas
 * @version 25-Mar-17.
 */
public class XY {
    public static final int EPSILON = 16;
    private final int x;
    private final int y;

    /**
     * Constructor.
     */
    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    /**
     * Getter.
     */
    public int getX() {
        return x;
    }

    /**
     * Getter.
     */
    public int getY() {
        return y;
    }

    /**
     * Detecta si this punto es cercano a Q.
     */
    public boolean isCloseTo(XY Q) {
        if (Game.getState() == Game.TESTING)
            Nap.sleep(100);
        return distance(Q) < EPSILON;
    }

    /**
     * Detecta si this punto es cercano al segmento P1-P2.
     */
    public boolean isCloseTo(XY P1, XY P2) {
        if (Game.getState() == Game.TESTING)
            Nap.sleep(100);
        double d1 = distance(P1);
        double d2 = distance(P2);
        if (d1 < EPSILON)
            return true;
        if (d2 < EPSILON)
            return true;
        double d12 = P1.distance(P2);
        if (d12 < EPSILON)
            return false;

        int xp = getX();
        int yp = getY();
        int x1 = P1.getX();
        int y1 = P1.getY();
        int x2 = P2.getX();
        int y2 = P2.getY();

        double dx = x2 - x1;
        double dy = y2 - y1;
        if (Math.abs(dx) < 3)   /* linea vertical: xm = x1*/
            return check(d1, d2, distance(x1, yp));
        if (Math.abs(dy) < 3)   /* linea horizontal: ym = y1 */
            return check(d1, d2, distance(xp, y1));

        // recta P1-P2:  ax + by + c = 0
        double a = dy;
        double b = -dx;
        double c = dx * y1 - dy * x1;

        // perpendicular:  -bx + ay + c2 = 0
        double a2 = -b;
        double b2 = a;
        double c2 = b * xp - a * yp;

        // proyeccion M de P sobre r
        double ym = (a * c2 - c * a2) / (b * a2 - a * b2);
        double xm = -(b * ym + c) / a;

        // distancia del punto a la recta
        double pmx = xp - xm;
        double pmy = yp - ym;
        double dm = Math.sqrt(pmx * pmx + pmy * pmy);
        return check(d1, d2, dm);
    }

    private double distance(XY Q) {
        return distance(Q.x, Q.y);
    }

    private double distance(int qx, int qy) {
        double dx = qx - this.x;
        double dy = qy - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    private boolean check(double d1, double d2, double dm) {
        // M debe estar entre P1 y P2
        if (dm < d1)
            return false;
        if (dm < d2)
            return false;

        // si P esta lo bastante cerca de la trayectoria
        return dm < EPSILON;
    }
}
