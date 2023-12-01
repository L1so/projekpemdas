import processing.core.PApplet;

public class gambar_jalan extends PApplet {

    float duration;
    float carX = 0;
    float awanX = 0;
    int bgColor = color(255, 250, 250); // Background color (blue)
    int houseColor = color(139, 137, 137); // House color (yellow)
    int atap = color (	255, 222, 185);
    int pintu = color (105, 105, 105);
    int line = color (139, 105, 105);
    int numberOfDrops = 100; // Jumlah tetesan hujan
    float[] dropX = new float[numberOfDrops];
    float[] dropY = new float[numberOfDrops];

    public static void main(String[] args) {
        gambar_jalan design = new gambar_jalan();
        PApplet.runSketch(new String[]{"Hello Processing"}, design);
    }

    @Override
    public void settings() {
        size(1366, 768);

        // Inisialisasi posisi awal tetesan hujan
        for (int i = 0; i < numberOfDrops; i++) {
            dropX[i] = random(width);  // Posisi awal x secara acak
            dropY[i] = random(height); // Posisi awal y secara acak
        }
    }

    public void strukturAwan(float x, float y, float width, float height) {
        ellipse(x, y, width, height);
        ellipse(x + width / 3, y - height / 3, width, height);
        ellipse(x + 2 * width / 3, y, width, height);
    }

    public void awan(float x) {
        noStroke();
        fill(109, 110, 109); // Set fill color to white for the clouds
        strukturAwan(x, 80, 70, 40);
        strukturAwan(x + 200, 40, 70, 40);
        strukturAwan(x + 160, 110, 80, 40);
        stroke(0);
    }
    public void mobil(float x, float y) {
        // Draw the car body
        fill(255, 0, 0); // Set fill color to red for the car body
        rect(x, y, 230, 60); // Draw a rectangle for the car body

        // Draw the car roof
        fill(0, 0, 255); // Set fill color to blue for the car roof
        rect(x + 10, y - 40, 140, 40); // Draw a rectangle for the car roof

        // JENDELA
        fill(150, 255, 255);
        rect(x + 80, y - 30, 60, 26);


        // Draw the wheels
        fill(0); // Set fill color to black for the wheels
        ellipse(x + 40, y + 80, 40, 40); // Draw the left wheel
        ellipse(x + 200, y + 80, 40, 40); // Draw the right wheel
    }
    public void rumah(float x, float y) {
        pushMatrix();
        translate(x, y);
        stroke(0, 0, 0);
        strokeWeight(3);

        // Draw house body
        fill(255, 200, 150); // Set house color to light orange
        rect(-100, 50, 200, 150); // House body

        // Draw roof
        fill(150, 75, 0); // Set roof color to brown
        triangle(-100, 50, 0, -50, 100, 50); // Roof

        // Draw door
        fill(100, 50, 0); // Set door color to dark brown
        rect(-30, 100, 60, 100); // Door

        // Draw doorknob
        fill(255, 255, 0); // Set doorknob color to yellow
        ellipse(15, 150, 10, 10); // Doorknob

        // Draw windows
        fill(200, 255, 255); // Set window color to light blue

        // Left window
        rect(-80, 70, 40, 40);
        line(-60, 70, -60, 110);
        line(-80, 90, -40, 90);

        // Right window
        rect(40, 70, 40, 40);
        line(60, 70, 60, 110);
        line(40, 90, 80, 90);
        stroke(0);
        strokeWeight(1);
        popMatrix();
    }
        public void pohon(float x) {
            // Batang pohon
            fill(74, 27, 4);
            rect(20 + x, 345, 35,200 );
            // Dedaunan
            fill(41, 148, 3);
            ellipse(35 + x, 345, 100, 100);
            // Apel
            fill(207, 4, 18);
            ellipse(60 + x, 325, 10, 10);
            ellipse(38 + x, 345, 10, 10);
            ellipse(29 + x, 380, 10, 10);
            ellipse(5 + x, 330, 10, 10);
            ellipse(48 + x, 350, 10, 10);
            ellipse(60 + x, 370, 10, 10);
            ellipse(16 + x, 345, 10, 10);
            ellipse(31 + x, 370, 10, 10);
        }

    @Override
    public void draw() {
        background(135, 206, 250);
        fill(100, 100, 100);
        rect(0,545,1366,150);
        // JALAN RAYA
        for (int k = 1; k <= 11; k++) {
            fill(245, 243, 240);
            rect(1366/12*k, 605, 115, 15);
            k += 2;
        }
        // TANAH
        fill(135, 99, 62);
        rect(0, 696, 1366, 70);
        rumah(230, 345);
        rumah(530, 345);
        rumah(830, 345);
        rumah(1130, 345);
        // Draw the moving car
        pohon(45);
        pohon(350);
        pohon(655);
        pohon(960);
        pohon(1265);
        mobil(carX, 575);
        awan(awanX);

        // Move the car to the right
        carX += 5;
        awanX += 2;

        // Reset the car's position when it goes off the screen
        if (carX > width + 50) {
            carX = -50;
        }
        if (awanX > width + 50) {
            awanX = -50;
        }
        drawRaindrops();
    }

    // Fungsi untuk menggambar dan menggerakkan tetesan hujan
    public void drawRaindrops() {
        fill(150, 200, 255); // Warna tetesan hujan (biru muda)

        for (int i = 0; i < numberOfDrops; i++) {
            ellipse(dropX[i], dropY[i], 5, 10); // Menggambar tetesan hujan
            dropY[i] += 5; // Menggerakkan tetesan hujan ke bawah

            // Jika tetesan hujan mencapai batas bawah layar, reset ke atas
            if (dropY[i] > height) {
                dropY[i] = 0;
                dropX[i] = random(width);
            }
        }
    }
}
