package design_patterns.structural_patterns;

interface AbstractPostManager {
    void showPosts();

    void publishPost();
}

class PostManager implements AbstractPostManager {

    // costly methods
    // access to these methods should be controlled
    @Override
    public void showPosts() {

        System.out.println("Posts are coming on the way");
    }

    @Override
    public void publishPost() {
        System.out.println("Post is on the way to get published");
    }

}

//// the above layer represents the module whose object's access should be
//// controlled

// the proxy

class PostManagerProxy {

    private AbstractPostManager postManager;

    PostManagerProxy() {
        if (postManager == null) {
            postManager = new PostManager();
        }
    }

    public void publishPost(UserPlan plan) {

        // it's a costly operation, block that operation if the user is signed out or
        // has a free subscription plan
        // it's a (business rule) for many apps now

        if (plan == UserPlan.FreePlan) {
            System.out.println("You are a free member, please update your subscription to Premium to post on site");
        } else if (plan == UserPlan.SubscriptionPlan) {
            postManager.publishPost();
        }

        // so it's called a proxy , because it decides when to access that object based
        // on some situation.

    }

    public void showPosts() {

        // its relatively sheep operation (don't restrict access to that functionality )

        postManager.showPosts();
    }

}

// additional modeule that represent user status either it hase a free or
// premium plan
enum UserPlan {

    FreePlan,
    SubscriptionPlan,
}