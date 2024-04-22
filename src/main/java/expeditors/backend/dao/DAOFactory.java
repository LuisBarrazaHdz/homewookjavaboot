package expeditors.backend.dao;

import expeditors.backend.dao.inmemory.InMemoryAdopterDAO;
import expeditors.backend.dao.jpa.JpaAdopterDAO;
import expeditors.backend.service.AdopterService;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class DAOFactory {
    /*
    private static Map<String, Object> objects = new ConcurrentHashMap<>();

    private static ResourceBundle bundle = ResourceBundle.getBundle("environment");
    private static String profile;
    static {
        profile = bundle.getString("environment.profile");
    }


    public static AdopterDAO adopterDAO() {
        return switch(profile) {
            case "dev" -> new InMemoryAdopterDAO();
            case "prod" -> new JpaAdopterDAO();
            default -> throw new RuntimeException("Unknown profile: " + profile);
        };
    }

    public static AdopterDAO adopterDAO() {
        var result = switch(profile) {
            case "memory" -> {
                var current = (AdopterDAO) objects.get("adopterDAO");
                if(current == null) {
                    current = new InMemoryAdopterDAO();
                    objects.put("adopterDAO", current);
                }
                yield current;
            }
            case "jpa" -> (AdopterDAO)objects.computeIfAbsent("adopterDAO", (key) -> {return new JpaAdopterDAO();});
            default -> throw new RuntimeException("Unknown profile: " + profile);
        };

        return result;
    }
    public static AdopterService adopterService() {
        AdopterService service =
                (AdopterService) objects.computeIfAbsent("adopterService",
                        k -> {
                            AdopterService as = new AdopterService();
                            AdopterDAO dao = adopterDAO();
                            as.setAdopterDAO(dao);
                            return as;
                        });


        return service;
    }
    */
}
