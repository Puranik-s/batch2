package com.abc.medicine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.abc.medicine.entity.Medicine;
import com.abc.medicine.service.MedicineService;
import java.util.List;
import java.util.Locale.Category;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }


	 @GetMapping("/all")
	    public ResponseEntity<List<Medicine>> getAllMedicines() {
	        List<Medicine> medicines = medicineService.getAllMedicines();
	        return ResponseEntity.ok(medicines);
	    }


	 @GetMapping("/{id}")
		public ResponseEntity<Medicine> fetchMedicineDetails(@PathVariable("id") int medieId) {
			Medicine medicine = medicineService.getMedicineById(medieId);
				return new ResponseEntity<>(medicine,HttpStatus.OK);
		}

	 @PostMapping("/save")
	    public ResponseEntity<Medicine> saveMedicine(@RequestBody Medicine medicine) {
	        medicineService.saveMedicine(medicine);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }

	 @PutMapping("/update")
		public  ResponseEntity<Medicine> editMedicine(@RequestBody Medicine medicine) {
			medicineService.updateMedicine(medicine);
			ResponseEntity<Medicine> responseEntity = new ResponseEntity<>(medicine,HttpStatus.OK);
			return responseEntity;
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteMedicine(@PathVariable("id") int medieId) {
			medicineService.deleteMedicine(medieId);
			ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
			return responseEntity;	
		}
	 @GetMapping("/category/{categoryName}")
	    public ResponseEntity<List<Medicine>> getMedicinesByCategory(@PathVariable String categoryName) {
	        List<Medicine> medicines = medicineService.getMedicinesByCategory(categoryName);
	        return ResponseEntity.ok(medicines);
	    }

}