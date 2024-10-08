package edu.bu.cs622.path;

import java.util.ArrayList;
import java.util.List;

public class Path {
  private List<Step> path;

  public Path() {
    this.path = new ArrayList<>();
  }

  public void addStep(Step step) {
    this.path.add(step);
  }

  public List<Step> getSteps() {
    return new ArrayList<>(path);
  }

}
