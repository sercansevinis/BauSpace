
public class ShotPowerUp extends PowerUp {
protected boolean isActive;

public ShotPowerUp()
{
	isActive=false;
	duration=0;
}

public boolean isActive() {
	return isActive;
}

public void setActive(boolean isActive) {
	this.isActive = isActive;
}

}
