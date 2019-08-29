package com.demo.jdk8.collectors;

import com.demo.jdk8.collectors.model.Artist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhoukai
 * @date 2018-10-16
 */
public class PartitioningByTest {

    @Test
    void testPartitioningBy() {

        Artist nightwish = new Artist();
        nightwish.setName("Nightwish");
        nightwish.setMainMusician("Floor Jansen");
        nightwish.setMembers(7);
        nightwish.setSolo(Boolean.FALSE);
        List<String> trackListNightwish = new ArrayList<>();
        trackListNightwish.add("《She is My Sin》");
        trackListNightwish.add("《Bye Bye Beautiful》");
        trackListNightwish.add("《Nemo》");
        nightwish.setTrackList(trackListNightwish);

        Artist yoga = new Artist();
        yoga.setName("林宥嘉");
        yoga.setMainMusician("");
        yoga.setSolo(Boolean.TRUE);
        yoga.setMembers(1);
        List<String> trackListYoga = new ArrayList<>();
        trackListYoga.add("《说谎》");
        trackListYoga.add("《全世界谁倾听你》");
        trackListYoga.add("《成全》");
        trackListYoga.add("《残酷月光》");
        yoga.setTrackList(trackListYoga);

        List<Artist> list = new ArrayList<>();
        list.add(nightwish);
        list.add(yoga);

        System.out.println("=====>获得members最多的一条：");
        Artist maxMembersArtist = list.stream().collect(Collectors.maxBy(Comparator.comparing(Artist::getMembers))).get();
        System.out.println(maxMembersArtist.toString());
        System.out.println("=====>获取members最少的一条：");
        Artist minMembersArtist = list.stream().collect(Collectors.minBy(Comparator.comparing(Artist::getMembers))).get();
        System.out.println(minMembersArtist);

        System.out.println("=====>");
        Artist biggestGroup = biggestGroup(list.stream()).get();
        System.out.println(biggestGroup);

        System.out.println("=====>");
        Artist smallestGroup = smallestGroup(list).get();
        System.out.println(smallestGroup);
    }

    static public Optional<Artist> biggestGroup(Stream<Artist> artists) {

        Artist artist = artists.max(Comparator.comparing(Artist::getMembers)).get();
        return Optional.ofNullable(artist);
    }

    static public Optional<Artist> smallestGroup(List<Artist> artists) {

        Artist artist = artists.stream().min(Comparator.comparing(Artist::getMembers)).get();
        return Optional.ofNullable(artist);
    }
}
