package ro.info.iasi.fiipractic.actuator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "release-note")
public class CustomEndpoint {
  private final Map<String, List<String>> releaseNotesMap = new HashMap<>();

  public CustomEndpoint() {
    releaseNotesMap.put("v1", List.of("Feature one released", "Creating resource bug fixed."));
    releaseNotesMap.put("v2", List.of("Feature two released", "Various bug fixed."));
  }

  @ReadOperation
  public Map<String, List<String>> getReleaseNotesMap() {
    return releaseNotesMap;
  }

  @ReadOperation
  public List<String> getReleaseNotesByKey(@Selector String key) {
    return releaseNotesMap.get(key);
  }

  @WriteOperation
  public void addReleaseNotes(@Selector String key, String value) {
    releaseNotesMap.put(key, Arrays.asList(value.split(",")));
  }
}