package solarsystem.model.blenderobjects.core;

//-------------------------  inner Class  -----------------------------

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.net.URL;
import java.util.Objects;

/* A Material object holds colour and texture information
for a named material.
The Material object also manages the rendering using its
colours (see setMaterialColors()). The rendering using the
texture is done by the Materials object.
 */
public class Material {

    private String name;
    // colour info
    private Tuple3 ka;
    private Tuple3 kd;
    private Tuple3 ks; // ambient, diffuse, specular colours
    private float ns;
    private float d; // shininess and alpha
    // texture info
    private String texFnm;
    private Texture texture;

    public Material(String nm) {
        name = nm;

        d = 1.0f;
        ns = 0.0f;
        ka = null;
        kd = null;
        ks = null;

        texFnm = null;
        texture = null;
    } // end of Material()

    public void showMaterial() {
        System.out.println(name);
        if (ka != null) {
            System.out.println("  Ka: " + ka.toString());
        }
        if (kd != null) {
            System.out.println("  Kd: " + kd.toString());
        }
        if (ks != null) {
            System.out.println("  Ks: " + ks.toString());
        }
        if (ns != 0.0f) {
            System.out.println("  Ns: " + ns);
        }
        if (d != 1.0f) {
            System.out.println("  d: " + d);
        }
        if (texFnm != null) {
            System.out.println("  Texture file: " + texFnm);
        }
    } // end of showMaterial()

    public boolean hasName(String nm) {
        return name.equals(nm);
    }
    // --------- set/get methods for colour info --------------

    public void setD(float val) {
        d = val;
    }

    public float getD() {
        return d;
    }

    public void setNs(float val) {
        ns = val;
    }

    public float getNs() {
        return ns;
    }

    public void setKa(Tuple3 t) {
        ka = t;
    }

    public Tuple3 getKa() {
        return ka;
    }

    public void setKd(Tuple3 t) {
        kd = t;
    }

    public Tuple3 getKd() {
        return kd;
    }

    public void setKs(Tuple3 t) {
        ks = t;
    }

    public Tuple3 getKs() {
        return ks;
    }

    public void setMaterialColors(GL2 gl) {

        System.out.println(" --- SET MATERIAL COLOR ---");
        if (ka != null) {
            // ambient color
            float[] colorKa = {ka.getX(), ka.getY(), ka.getZ(), 1.0f};
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, colorKa, 0);
        }
        if (kd != null) {
            // diffuse color
            float[] colorKd = {kd.getX(), kd.getY(), kd.getZ(), 1.0f};
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, colorKd, 0);
        }
        if (ks != null) {
            // specular color
            float[] colorKs = {ks.getX(), ks.getY(), ks.getZ(), 1.0f};
            gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, colorKs, 0);
        }

        if (ns != 0.0f) {
            // shininess
            gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, ns);
        }

        if (d != 1.0f) { // alpha
            // not implemented
        }
    } // end of setMaterialColors()

    // --------- set/get methods for texture info --------------
    public void loadTexture(GL2 gl, String fnm) {
        try {
            texFnm = fnm;
            //texture = TextureIO.newTexture(new File(texFnm), false);
            URL url = ClassLoader.getSystemResource(fnm);
            Objects.requireNonNull(url, () -> "Texture not found:" + fnm);
            texture = TextureIO.newTexture(url, false, ""); // fixme: there's also a AWSTextureIO
            texture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
            texture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
        } catch (Exception e) {
            System.out.println("Error loading texture " + texFnm);
            e.printStackTrace();
        }
    } // end of loadTexture()

    public void setTexture(Texture t) {
        texture = t;
    }

    public Texture getTexture() {
        return texture;
    }
} // end of inner Class Material