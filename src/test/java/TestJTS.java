import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by dengc on 2016/12/18.
 */
public class TestJTS {
    public static void main(String[] args){
        GeometryJSON geoJson = new GeometryJSON();
        String json = "{ \"type\": \"LineString\",\n" +
                "    \"coordinates\": [ [100.0, 0.0], [101.0, 1.0] ]\n" +
                "    }";
        Reader reader = new StringReader(json);
        try {
            Geometry geometry = geoJson.read(reader);
            geometry.setSRID(4326);
            int i = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
