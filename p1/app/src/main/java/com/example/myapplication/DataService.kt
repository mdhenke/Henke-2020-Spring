package com.example.myapplication

object DataService {
    private val jobsList =  mutableListOf<JobModel>()

    fun initializeJobList() {
        jobsList.add(JobModel("Google", "Software Engineer Intern"))
        jobsList.add(JobModel("Facebook", "Software Engineer Intern"))
        jobsList.add(JobModel("Microsoft", "Front End Software Engineer Intern"))
        jobsList.add(JobModel("Amazon", "Data Science Intern"))
    }

    fun getJobs() : List<JobModel> {
        return jobsList
    }

}