# Feature Branches
[Feature branches](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-branches) provide a way to test and implement new features without disrupting with the main branch. 

- Create your new branch
    - `git checkout -b <branchName>`
- Commit and push your code changes to that branch
- Continue to update your main/master branch as you progress
    - `git checkout master`
    - `git pull`
- If any changes have occured to the main branch, merge these changes into your feature branch
    - `git checkout <branchName>`
    - `git merge main`
- Deal with any merge conflicts at this time
- Continue these steps until the feature is brought to completion 

# Pull Requests
When a branch is ready to be merged into the main branch, open a [pull request](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/about-pull-requests) on GitHub to alert others that it is ready and requires review.

- Open your GitHub repository and click on the `branches` button
- Locate the branch you wish to merge to master and click `New pull request`
- Write the changes you made and click `Create pull request`
- When sufficient review has occured, click either `Merge pull request` or `Squash and Merge` to merge it into the master branch

## Merge vs Squash
Merges will move all the commits from the feature branch into the target branch; Squash and Merging will condense the changes down to one commit, then merge the commit into the target branch.
- Favor `Merge` when commits involve more than one author or are targetting many issues/features
- Favor `Squash` when commits involve one author and are targetting a specific issue/feature
