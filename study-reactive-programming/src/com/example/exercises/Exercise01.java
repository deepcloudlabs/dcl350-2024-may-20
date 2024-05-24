package com.example.exercises;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Exercise01 {

	public static void main(String[] args) {
		var bs = new BusinessService();
		System.err.println("Before sync call...");
		var result = bs.workHard();
		System.err.println("result is %d".formatted(result));
		System.err.println("After sync call...");
		// Asynchronous Programming++
		System.err.println("Before async call...");
		bs.workHardAsync().thenAcceptAsync( res -> System.err.println("result is %d".formatted(res)));
		System.err.println("After async call...");
		for (var i=0;i<100;i++) {
			System.err.println("Doing something else...[%d]".formatted(i));
			try {TimeUnit.MILLISECONDS.sleep(70);}catch (Exception e) {}			
		}
	}

}

class BusinessService {
	public int workHard() { // sync
		try {TimeUnit.SECONDS.sleep(5);}catch (Exception e) {}
		return 42;
	}
	public CompletableFuture<Integer> workHardAsync() { // async
		return CompletableFuture.supplyAsync(() ->{
			try {TimeUnit.SECONDS.sleep(5);}catch (Exception e) {}
			return 42;			
		});
	}
}
