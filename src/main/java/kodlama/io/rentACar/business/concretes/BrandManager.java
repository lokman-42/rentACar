package kodlama.io.rentACar.business.concretes;
import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.responceses.GetAllBrandResponse;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;

    @Autowired
    public BrandManager(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<GetAllBrandResponse> getAll() {
        // iş kuralları
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandResponse> brandResponses =new ArrayList<GetAllBrandResponse>();

        for (Brand brand:brands
             ) {
            GetAllBrandResponse responseItem = new GetAllBrandResponse();
            responseItem.setId(brand.getId());
            responseItem.setName(brand.getName());
            brandResponses.add(responseItem);
        }
        return brandResponses;
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        Brand brand = new Brand();
        brand.setName(createBrandRequest.getName());
        this.brandRepository.save(brand);
    }
}
