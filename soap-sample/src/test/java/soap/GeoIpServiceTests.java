package soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;
import com.lavasoft.GetIpLocation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {
    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("92.101.253.82");
        System.out.println(ipLocation);
    }
}
