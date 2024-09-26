package me.roundaround.truesight;

public class Tracker {
  private static Tracker instance;

  public static Tracker getInstance() {
    if (instance == null) {
      instance = new Tracker();
    }
    return instance;
  }

  private boolean enabled = false;
  private boolean installedOnServer = false;

  private Tracker() {
  }

  public boolean isEnabled() {
    return this.installedOnServer && this.enabled;
  }

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  public boolean isModActivated() {
    return this.installedOnServer;
  }

  public void deactivateMod() {
    this.enabled = false;
    this.installedOnServer = false;
  }

  public void activateMod() {
    this.installedOnServer = true;
  }

  public void toggle() {
    if (!this.isModActivated()) {
      return;
    }
    this.enabled = !this.enabled;
  }
}
