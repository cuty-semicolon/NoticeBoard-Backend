//package com.example.board.web;
//
//
//import com.example.board.domain.posts.Posts;
//import com.example.board.domain.posts.PostsRepository;
//import com.example.board.dto.PostsResponseDto;
//import com.example.board.dto.PostsSaveRequestDto;
//import com.example.board.dto.PostsUpdateRequestDto;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//
//import java.util.List;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PostsApiControllerTest {
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    @AfterEach
//    public void tearDown() throws Exception {
//        postsRepository.deleteAll();
//    }
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @BeforeEach
//    public void setup(){
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .build();
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    public void Posts_등록된다() throws Exception {
//        //given
//        String title = "title";
//        String content = "content";
//        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder().title(title).content(content).author("author").build();
//        String url = "http://localhost:" + port + "/api/v1/posts";
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//
//        //then
//        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Posts> all = postsRepository.findAll();
//        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(title);
//        Assertions.assertThat(all.get(0).getContent()).isEqualTo(content);
//
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    public void Posts_수정된다() throws Exception{
//        //given
//        Posts savedPosts = postsRepository.save(Posts.builder()
//                .title("title")
//                .content("content")
//                .author("author")
//                .build());
//
//        Long updateId =savedPosts.getId();
//        String expectedTitle = "title2";
//        String expectedContent = "content2";
//
//        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
//                .title(expectedTitle)
//                .content(expectedContent)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
//        HttpEntity<PostsResponseDto> requestEntity = new HttpEntity(requestDto);
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//
//        //then
//        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Posts> all = postsRepository.findAll();
//        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
//        Assertions.assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
//    }
//}
