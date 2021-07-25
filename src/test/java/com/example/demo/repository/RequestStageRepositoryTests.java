package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.domain.Request;
import com.example.demo.domain.RequestStage;
import com.example.demo.domain.User;
import com.example.demo.domain.enums.RequestState;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class) // Executar teste na ordem que indicar em cada metodo
@SpringBootTest
public class RequestStageRepositoryTests {
	@Autowired
	private RequestStageRepository requestStageRepository;

	@Test
	@Order(1)
	public void saveTest() {
		User owner = new User();
		owner.setId(1L);

		Request request = new Request();
		request.setId(1L);
		RequestStage stage = new RequestStage(null, "Foi comprado um novo laptop de marca HP e com 16 GB de RAM",
				new Date(), RequestState.CLOSED, request, owner);

		RequestStage createdStage = requestStageRepository.save(stage);

		assertThat(createdStage.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(2)
	public void updateTest() {
		User owner = new User();
		owner.setId(1L);

		Request request = new Request();
		request.setId(1L);
		
		RequestStage stage = new RequestStage(1L, "Foi comprado um novo laptop de marca HP e com 16 GB de RAM, estou ancioso",
				new Date(), RequestState.CLOSED, request, owner);

		RequestStage uptadatedStage = requestStageRepository.save(stage);

		assertThat(uptadatedStage.getDescription()).isEqualTo("Foi comprado um novo laptop de marca HP e com 16 GB de RAM, estou ancioso");
		
	}

	@Test
	@Order(3)
	public void getByIdTest() {
		Optional<RequestStage> result = requestStageRepository.findById(1L);
		RequestStage stages = result.get();

		assertThat(stages.getDescription()).isEqualTo("Foi comprado um novo laptop de marca HP e com 16 GB de RAM");
	}

	@Test
	@Order(4)
	public void listRequestTest() {
		List<RequestStage> stages = requestStageRepository.findAll();
		assertThat(stages.size()).isEqualTo(1);
	}

	@Test
	@Order(5)
	public void listByRequestIdTest() {
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(1L);
		assertThat(stages.size()).isEqualTo(1);
	}
}
