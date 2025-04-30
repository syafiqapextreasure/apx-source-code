package com.ppk.topController.membership.renual;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.ppk.topController.membership.renual.entity.RenewFee;
import com.ppk.topController.membership.renual.entity.RenewalMembershipData;
import com.ppk.topController.membership.renual.service.GetFndGlnumbService;
import com.ppk.topController.membership.renual.service.RenewFeeService;
import com.ppk.topEntity.formsEntity.registration.Encrypter;

@RestController
public class MembershipRenuwalformCkntroller {
	private static final Logger logger = LoggerFactory.getLogger(MembershipRenuwalformCkntroller.class);
	@Autowired
	private RenewFeeService renewFeeService;

	@Autowired
	GetFndGlnumbService getFndGlnumbService;

	@GetMapping("/getRenewFee")
	public List<RenewFee> getRenewFee(@RequestParam String patronId,HttpServletRequest request) {
		User user=	getLiferayUserDetails(request);
		logger.error(patronId+"---user is --"+user);
		logger.info(patronId+"---user is --"+user);
		return renewFeeService.getRenewalFeeByPatronId(patronId);
	}

	@GetMapping("/membership")
	public List<RenewalMembershipData> getMembershipData(@RequestParam String id,HttpServletRequest request) {
		User user=	getLiferayUserDetails(request);
		//logger.error(id+"---user is --"+user.getScreenName());
		logger.info(id+"---user is --"+user);
		return renewFeeService.getMembershipData(id);
	}

	@PostMapping("handleRenewalMembership")
	public ResponseEntity<Object> handleRenewalMembership(@RequestParam Map<String, String> params,HttpServletRequest request)
			throws UnknownHostException {
		try {
			String patronId = params.get("patronId");
		User user=	getLiferayUserDetails(request);
		//System.out.println("patronId is "+patronId+" user details "+user.getUserId());
			String expDate = params.get("expDate");
			String newExpDate = params.get("newExpDate");
			String recordedBy = params.get("recordedBy");
			String fee = params.get("fee");
			System.out.println("Form rquest for renewal " + patronId + "expDate " + expDate + " newExpDate "
					+ newExpDate + "recordedBy " + recordedBy + " fee " + fee);
			boolean bSuccessful = false;
			String patrbrnc = renewFeeService.getPatronBranch(patronId);

			// No fee renewal
			if ("0".equals(fee) || "0.00".equals(fee)) {
				bSuccessful = renewFeeService.updatePatronExpdate(patronId, newExpDate);
				System.out.println("if bSuccessful is " + bSuccessful);
				if (bSuccessful) {
					String gsModule = "GL";
					renewFeeService.insertAudit(gsModule, "GLU0006", patronId + ", " + expDate + ", " + newExpDate,
							"onlineform");
					String colmm = "GL14EXPDATE";
					String mode = "U";
					renewFeeService.insertGLPATA(patronId, colmm, expDate, newExpDate, recordedBy, mode);
					return ResponseEntity.ok("Success");
				}
			} else {
				String year = params.get("year");
				getFndGlnumbService.updatingGLNUMB("BILLNO");
				int iCounterbill = getFndGlnumbService.getGlnumb2("BILLNO");
				String sgenerateBillno = String.format("%010d", iCounterbill);

				bSuccessful = getFndGlnumbService.updatePatrStatRenewal(patronId);
				System.out.println("else bSuccessful is " + bSuccessful);

				try {
					String patrcate = getFndGlnumbService.getPatronCatnDesc(patronId);
					String[] arrayPatrCateVal = patrcate.split("=");
					System.out.println(arrayPatrCateVal + "patrcate is " + patrcate);
					logger.error("patronId is "+patronId);
					if (bSuccessful) {
						getFndGlnumbService.updatingGLNUMB("TRXNO");
						int iCounter = getFndGlnumbService.getGlnumb2("TRXNO");

						String renewcode = getFndGlnumbService.getFndPatronRenewFee(arrayPatrCateVal[1].trim());
						System.out.println("renewcode is " + renewcode);
						if (renewcode != null) {
							bSuccessful = getFndGlnumbService.insertRETRXN(iCounter, renewcode, fee, patronId,
									patrbrnc + "_ADMIN", year, sgenerateBillno);
							System.out.println("bSuccessful is "+bSuccessful);
							if (bSuccessful) {
								String gsModule = "GL";
								renewFeeService.insertAudit(gsModule, "GLU0006",
										patronId + ":" + year + ", " + expDate + ", " + newExpDate, "onlineform");
								String colmm = "GL14EXPDATE";
								String mode = "U";
								System.out.println("expDatei "+expDate);
								boolean res=renewFeeService.insertGLPATA(patronId, colmm, expDate, newExpDate, recordedBy, mode);
								System.out.println("res is "+res);
								Map m=new HashMap<String, String>();
								m.put("sgenerateBillno", sgenerateBillno);
								m.put("sgenerateBillno", "https://wilmudev.ppj.gov.my/fpx/PaymentProcess?bil="+Encrypter.encrypt(sgenerateBillno));
								m.put("message","Success");
								return ResponseEntity.ok(m);
							}
						} else {
							return ResponseEntity.status(404)
									.body("Record Not found for GL07CATE:" + arrayPatrCateVal[1].trim());
						}
					}
				} catch (Exception ex) {
					System.out.println("bSuccessful is " + bSuccessful);
					ex.printStackTrace();
					return ResponseEntity.status(500).body("An error occurred");
				}
			}
		} catch (Exception ex) {

			ex.printStackTrace();
			return ResponseEntity.status(500).body("An error occurred");
		}
		return null;
	}
	public User getLiferayUserDetails(HttpServletRequest request) {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        if (themeDisplay != null) {
        	System.out.println("themeDisplay.getUser details who has loggedin "+themeDisplay.getUser());
            return themeDisplay.getUser();
        }

        return null;
    }
}
