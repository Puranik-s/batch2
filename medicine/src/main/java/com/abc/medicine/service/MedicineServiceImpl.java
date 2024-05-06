package com.abc.medicine.service;

import com.abc.medicine.entity.Category;
import com.abc.medicine.entity.Medicine;
import com.abc.medicine.exception.ResourceNotFoundException;
import com.abc.medicine.repository.CategoryRepository;
import com.abc.medicine.repository.MedicineRepository;
import com.abc.medicine.service.MedicineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class MedicineServiceImpl implements MedicineService {

	private MedicineRepository medicineRepository;
	private CategoryRepository categoryRepository;

	@Autowired
	public MedicineServiceImpl(MedicineRepository medicineRepository, CategoryRepository categoryRepository) {
		this.medicineRepository = medicineRepository;
		this.categoryRepository = categoryRepository;
	}
	@Override
	public Medicine saveMedicine(Medicine medicine) {
		return medicineRepository.save(medicine);
	}
	@Override
	public Medicine getMedicineById(int medieId) {
		Optional<Medicine> optionalMedicine = medicineRepository.findById(medieId);
        if(optionalMedicine.isEmpty()) {
        	throw new ResourceNotFoundException("Medicine not existing with id: "+medieId);
        }
        Medicine medicine = optionalMedicine.get();
        return medicine;
	}
	@Override
	public List<Medicine> getAllMedicines() {
		return medicineRepository.findAll();
	}
	@Override
	public Medicine updateMedicine(Medicine medicine) {
		Optional<Medicine> optionalMedicine = medicineRepository.findById(medicine.getMedieId());
        if(optionalMedicine.isEmpty()) {
        	throw new ResourceNotFoundException("Medicine not existing with id: "+medicine.getMedieId());
        }
        medicineRepository.save(medicine);
        return medicine;
	}
	@Override
	public void deleteMedicine(int medieId) {
		Optional<Medicine> optionalMedicine = medicineRepository.findById(medieId);
        if(optionalMedicine.isEmpty()) {
        	throw new ResourceNotFoundException("Medicine not existing with id: "+medieId);
        }
        Medicine medicine = optionalMedicine.get();
        medicineRepository.delete(medicine);
	}
	@Override
	public List<Medicine> getMedicinesByCategory(String categoryName) {
		return medicineRepository.findByCategory_CategoryName(categoryName);
	}
	@Override
	public Medicine saveMedicine(int categoryId, Medicine medicine) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if (optionalCategory.isEmpty()) {
			throw new ResourceNotFoundException("Category not existing with id: " + categoryId);
		}
		Category category = optionalCategory.get();
		medicine.setCategory(category);
		return medicine;
	}
}