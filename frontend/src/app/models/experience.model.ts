export interface Experience {
  id?: number;
  title: string;
  company: string;
  position: string;
  description: string;
  location?: string;
  startDate?: string;
  endDate?: string;
  type?: string;
  skills?: string;
  achievements?: string;
  isCurrent?: boolean;
  companyUrl?: string;
  companyLogoUrl?: string;
}
