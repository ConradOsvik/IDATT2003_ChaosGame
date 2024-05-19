package edu.ntnu.stud.factory;

import edu.ntnu.stud.models.ChaosGameDescription;

public class ChaosGameDescriptionFactory implements ChaosGameDescriptionFactoryInterface {
  
  /**
   * @return
   */
  @Override
  public ChaosGameDescription createJuliaDescription() {
    return null;
  }
  
  /**
   * @return
   */
  @Override
  public ChaosGameDescription createBarnsleyDescription() {
    return null;
  }
  
  /**
   * @return
   */
  @Override
  public ChaosGameDescription createSierpinskiDescription() {
    return null;
  }
}
