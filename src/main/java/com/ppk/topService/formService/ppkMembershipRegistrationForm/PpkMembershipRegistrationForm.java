package com.ppk.topService.formService.ppkMembershipRegistrationForm;
import com.ppk.topEntity.formsEntity.*;
import java.util.List;

public interface PpkMembershipRegistrationForm {
	
	  //Preferred PPK Locations/Lokasi PPK Pilihan
	  public  List<GetFndBranch> getFndBranchCodeandDesc();
	  
	  public UserMembership saveMembeshipForm(UserMembership userMembership);

}
