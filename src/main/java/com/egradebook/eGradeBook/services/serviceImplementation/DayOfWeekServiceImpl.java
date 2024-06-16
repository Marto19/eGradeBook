package com.egradebook.eGradeBook.services.serviceImplementation;

import com.egradebook.eGradeBook.entities.DayOfWeekCatalog;
import com.egradebook.eGradeBook.exceptions.DayOfWeekNotFoundException;
import com.egradebook.eGradeBook.repositories.DayOfWeekRepository;
import com.egradebook.eGradeBook.services.DayOfWeekService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DayOfWeekServiceImpl implements DayOfWeekService {

    private final DayOfWeekRepository dayOfWeekRepository;

    @Override
    public DayOfWeekCatalog findDayOfWeekById(Long id) throws DayOfWeekNotFoundException {
        return dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new DayOfWeekNotFoundException("Day of week with ID: " + id + "not found"));
    }

    @Override
    public DayOfWeekCatalog createDayOfWeek(DayOfWeekCatalog dayOfWeek) {
        return null;
    }

    @Override
    public DayOfWeekCatalog updateDayOfWeek(DayOfWeekCatalog dayOfWeek) {
        return null;
    }

    @Override
    public void delteDayOfWeek(Long id) throws DayOfWeekNotFoundException {
        DayOfWeekCatalog dayOfWeekCatalog = dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new DayOfWeekNotFoundException("Day of week with ID: " + id + "not found"));

        dayOfWeekRepository.delete(dayOfWeekCatalog);
    }

    @Override
    public List<DayOfWeekCatalog> listdayOfWeek() {
        return dayOfWeekRepository.findAll();
    }

    @Override
    public DayOfWeekCatalog createDayOfWeek(String dayOfWeek) {
        DayOfWeekCatalog dayOfWeekCatalog = new DayOfWeekCatalog();
        dayOfWeekCatalog.setDay(dayOfWeek);
        return dayOfWeekRepository.save(dayOfWeekCatalog);
    }

    @Override
    public DayOfWeekCatalog updateDayOfWeek(Long dayOfWeekId, String dayOfWeek) throws DayOfWeekNotFoundException {
        DayOfWeekCatalog dayOfWeekCatalog = dayOfWeekRepository.findById(dayOfWeekId)
                .orElseThrow(() -> new DayOfWeekNotFoundException("Day of week with ID: " + dayOfWeekId + "not found"));

        dayOfWeekCatalog.setDay(dayOfWeek);

        return dayOfWeekRepository.save(dayOfWeekCatalog);
    }

}
