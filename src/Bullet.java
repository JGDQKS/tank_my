import java.awt.*;

public class Bullet {
    private static final int SPEED = 12;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();



    Rectangle rect = new Rectangle();

    private int x, y;
    private Dir dir;

    TankFrame tf = null;
    private Group group = Group.BAD;

    private boolean living = true;

    public Bullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.bullets.remove(this);
        }
//        Color c = g.getColor();
//        g.setColor(Color.lightGray);
//        g.fillOval(x+40, y+20, WIDTH, HEIGHT);
//        g.setColor(c);
        switch (dir) {
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;

            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;

        }
        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }
        rect.x = this.x;
        rect.y = this.y;


        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT)
            living = false;
    }

    public void collideWith(Tank tank) {

        if (this.group == tank.getGroup()) {
            return;
        }

        if (rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int ex = tank.getX() + Tank.getWIDTH() / 2 - Explode.WIDTH / 2;
            int ey = tank.getY() + Tank.getHEIGHT() / 2 - Explode.HEIGHT / 2;
            tf.explodes.add(new Explode(ex, ey, tf));
        }
    }

    private void die() {
        this.living = false;
    }
}
