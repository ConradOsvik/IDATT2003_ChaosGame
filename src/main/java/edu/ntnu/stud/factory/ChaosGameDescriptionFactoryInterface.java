package edu.ntnu.stud.factory;

import edu.ntnu.stud.models.ChaosGameDescription;

public interface ChaosGameDescriptionFactoryInterface {
    ChaosGameDescription createJuliaDescription();
    ChaosGameDescription createBarnsleyDescription();
    ChaosGameDescription createSierpinskiDescription();
}
