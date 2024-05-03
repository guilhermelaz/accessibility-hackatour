package com.accessibility.accessibility.core.interestpoint.Service;

import com.accessibility.accessibility.core.interestpoint.Repository.InterestPointRepository;
import com.accessibility.accessibility.core.interestpoint.domain.entities.InterestPoint;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class InterestPointService {

    private final InterestPointRepository interestPointRepository;


    public InterestPointService(InterestPointRepository interestPointRepository) {
        this.interestPointRepository = interestPointRepository;
    }

    public InterestPoint createInterestPoint(InterestPoint interestPoint) {
        String normalizedCity = normalize(interestPoint.getAddress().getCity());
        String normalizedInterestPointName = normalize(interestPoint.getName());

        Optional<InterestPoint> existingInterestPoint = interestPointRepository.findAll().stream()
                .filter(ip -> normalize(ip.getName()).equals(normalizedInterestPointName)
                        && normalize(ip.getAddress().getCity()).equals(normalizedCity))
                .findFirst();

        if (existingInterestPoint.isPresent()) {
            throw new IllegalArgumentException("An interest point with this name already exists in the city.");
        }

        return interestPointRepository.save(interestPoint);
    }

    private String normalize(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").toLowerCase();
    }

    public InterestPoint findById(Long id) {
        return interestPointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Interest Point not found"));
    }

    public List<InterestPoint> findAll() {
        try{
            return interestPointRepository.findAll();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while fetching interest points");
        }
    }
}
