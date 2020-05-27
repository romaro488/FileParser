package com.example.demo.service;

import com.example.demo.enums.Sort;
import com.example.demo.model.User;
import com.example.demo.service.ParseService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Tuple;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private ParseService parseService;

	private final ParseService<List<Tuple<String, Date>>> start;
	private final ParseService<List<Tuple<String, Date>>> end;

	public List<User> getSortedUsers(int limit) {
		return sortUsers(limit, Sort.ASC);
	}

	public List<User> sortUsers(int limit, Sort sort) {
		Map<String, Tuple<Date, Date>> map = new HashMap<>();
		for (Tuple<String, Date> t : start.findChampions()) {
			map.putIfAbsent(t.first, apply(t));
		}
		convertData(map);

		return convertTuplesToUsers(limit, sort, map);
	}

	private void convertData(Map<String, Tuple<Date, Date>> map) {
		end.findChampions().forEach(t -> {
			Tuple<Date, Date> tuple = map.get(t.first);
			if (tuple != null) {
				tuple.second = t.second;
				map.put(t.first, tuple);
			}
		});
	}

	private List<User> convertTuplesToUsers(int limit, Sort sort, Map<String, Tuple<Date, Date>> map) {
		return map.entrySet().stream()
				.filter(e -> e.getValue().first != null && e.getValue().second != null)
				.map(e -> new User(e.getKey(), e.getValue().first, e.getValue().second))
				.sorted((user1, user2) -> {
					long time = user1.getEndDate().getTime() - user1.getStartDate().getTime();
					long time2 = user2.getEndDate().getTime() - user2.getStartDate().getTime();

					if (sort == Sort.ASC) {
						return Long.compare(time, time2);
					}
					return Long.compare(time2, time);
				})
				.limit(limit)
				.collect(Collectors.toList());
	}

	private Tuple<Date, Date> apply(Tuple<String, Date> t) {
		Tuple<Date, Date> date = new Tuple<>();
		date.first = t.second;
		return date;
	}

}