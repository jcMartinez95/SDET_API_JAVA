package deserialize.Post;

public class Users {
		
		private String name;
		private String lastName;
		private String job;
		private String dateOfBirth;
		private String startDate;
		private int vacationDays;
		

		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public int getVacationDays() {
			return vacationDays;
		}
		public void setVacationDays(int vacationDays) {
			this.vacationDays = vacationDays;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getJob() {
			return job;
		}
		public void setJob(String job) {
			this.job = job;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

}
