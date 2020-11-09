package com.epam.java.training.hibernate.task1.main;

import com.epam.java.training.hibernate.task1.data.entity.Like;
import com.epam.java.training.hibernate.task1.data.entity.Post;
import com.epam.java.training.hibernate.task1.data.entity.TypePost;
import com.epam.java.training.hibernate.task1.data.entity.User;
import com.epam.java.training.hibernate.task1.service.impl.LikeServiceImpl;
import com.epam.java.training.hibernate.task1.service.impl.PostServiceImpl;
import com.epam.java.training.hibernate.task1.service.impl.UserServiceImpl;
import net.sf.ehcache.CacheManager;

import java.sql.Timestamp;
import java.time.LocalDate;

public class AppMain {


    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        PostServiceImpl postService = new PostServiceImpl();
        LikeServiceImpl likeService = new LikeServiceImpl();

        User user = new User();

        user.setId(1L);
        user.setBirthDate(LocalDate.now());
        user.setFirstName("Nick");
        user.setLastName("VN");
        userService.save(user);

        Post post = new Post();
        post.setId(1L);
        post.setUser(user);
        post.setTypePost(TypePost.ENTERTAINING);
        post.setText("New post");
        postService.save(post);

        Like like = new Like();
        like.setId(1L);
        like.setPost(post);
        like.setUserId(21L);
        like.setTimestamp(new Timestamp(System.currentTimeMillis()));
        likeService.save(like);


        int size = CacheManager.ALL_CACHE_MANAGERS.get(0)
                .getCache("com.epam.java.training.hibernate.task1.data.entity.Like").getSize();
        System.out.println("Size in cache: " + size);
        userService.close();
        postService.close();
        likeService.close();
    }
}
